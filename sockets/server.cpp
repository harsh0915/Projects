#include "cstdio"		 //major input out functions
#include "cstdlib"		 //dynamic memory management
#include "cstring"		 //string helpers and class
#include "unistd.h"		 //read and write functions for client-server communication
#include "sys/types.h"	 // defines size_t, pthread_attr_t and many other keywords
#include "sys/socket.h"	 //system calls related to sockets and some structures
#include "netinet/tcp.h" //The <netinet/tcp.h> header shall define the following macro for use as a socket option at the IPPROTO_TCP level:
#include "arpa/inet.h"	 //inhouses the sockaddr_in and vairous structures
#include "iostream"		 //conatins cin, cout and endl
#include "pthread.h"	 //posix thread
#define MAX 100

using namespace std;

pthread_t threadArray[10];

class Message
{

	char buf[MAX];

public:
	void print()
	{

		cout << buf << endl;
	}

	void deserialise(char *buffer)
	{

		memcpy(&buf, buffer, sizeof(buf));
	}

	void serialise(char *buffer)
	{

		memcpy(buffer, &buf, sizeof(buf));
	}

	void findSize(char *buffer)
	{

		int n = 0;
		while (buf[n] != '\0')
		{
			n++;
		}
		memset(buffer, '\0', sizeof(buffer));
		snprintf(buffer, sizeof(buf), "Size of the message recived is :%d", n - 1);
	}
};

void report(const char *msg, int terminate)
{

	perror(msg);
	if (terminate)
		exit(-1);
}

void *interact(void *value)
{

	Message msg;
	int *client_fd = (int *)value;
	char buffer[sizeof(msg)];

	while (1)
	{
		// recive from client
		read(*client_fd, buffer, sizeof(msg));

		msg.deserialise(buffer);
		msg.print();
		msg.findSize(buffer);

		// send to client
		write(*client_fd, buffer, sizeof(msg));
	}
}

int main()
{

	// intialising the socket
	int fd = socket(AF_INET, SOCK_STREAM, 0);

	if (fd < 0)
		report("socket intialisation error", 1);

	struct sockaddr_in saddr;

	memset(&saddr, 0, sizeof(saddr));
	saddr.sin_family = AF_INET;
	saddr.sin_addr.s_addr = htonl(INADDR_ANY);
	saddr.sin_port = htons(8070);

	// binding the server's local address in memory
	if (bind(fd, (sockaddr *)&saddr, sizeof(saddr)) < 0)
		report("binding error", 1);

	if (listen(fd, 5) < 0)
		report("listening error", 1);

	printf("\nServer listening on port\n");

	while (1)
	{

		struct sockaddr_in caddr;
		socklen_t len = sizeof(caddr);
		int client = 0; // int to track number of clients

		int *client_fd = (int *)malloc(sizeof(int));
		if (client_fd == NULL)
		{
			report("memory allocation failed", 1);
		}
		*client_fd = accept(fd, (sockaddr *)&caddr, &len);

		if (*client_fd < 0)
		{

			report("accept", 0);
		}
		else
		{

			if (pthread_create(&threadArray[client++], NULL, interact, client_fd) != 0)
				report("failed to create thread", 0);
		}

	} // outer while

	close(fd);

	return 0;
}
