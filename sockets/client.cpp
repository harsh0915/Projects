#include "cstdio"
#include "stdlib.h"
#include "string.h"
#include "unistd.h"
#include "sys/types.h"
#include "sys/socket.h"
#include "arpa/inet.h"
#include "netinet/in.h"
#include "netinet/tcp.h"
#include "netdb.h"
#include "iostream"
#include "pthread.h"

#define MAX 100

using namespace std;

class Message
{

	char buf[MAX];

public:

	void writeMsg()
	{
		memset(buf, '\0', MAX);
		cout << "\nWrite your message :" << endl;
		fgets(buf, MAX, stdin);
	};
	void print()
	{

		cout << buf << endl;
	}
	void serialise(char *buffer)
	{

		memcpy(buffer, &buf, sizeof(buf));
	};
	void deserialise(char *buffer)
	{

		memcpy(&buf, buffer, sizeof(buf));
	};
};

void report(const char *msg, int terminate)
{
	perror(msg);
	if (terminate)
		exit(-1);
}

int main()
{

	Message msg;
	int servSocket = socket(AF_INET, SOCK_STREAM, 0);

	if (servSocket < 0)
		report("socket", 1);

	struct sockaddr_in saddr;
	// storing server's information on a dedicated structure
	memset(&saddr, 0, sizeof(saddr));
	saddr.sin_family = AF_INET;
	saddr.sin_addr.s_addr = inet_addr("127.0.0.1");
	saddr.sin_port = htons(8070);

	if (connect(servSocket, (struct sockaddr *)&saddr, sizeof(saddr)) < 0)
		report("connect", 1);

	puts("\nConnected to server...\n");

	while (1)
	{

		int n = 0, size;
		msg.writeMsg();
		char buffer[sizeof(msg)];

		// send msg to server
		msg.serialise(buffer);
		write(servSocket, buffer, sizeof(msg));

		// recive msg from server
		read(servSocket, buffer, sizeof(msg));
		cout << buffer << endl;
	}

	cout << "Client done, about to exit...";

	close(servSocket); /* close the connection */

	return 0;
}
