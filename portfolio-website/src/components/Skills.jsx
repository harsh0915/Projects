import { forwardRef } from "react";
import Timeline from "./Timeline"

const Skills = forwardRef(({ navbar_height }, ref) => {

	const skills = [
		{
			heading: "Android(Java)",
			content:
				`Networking: OkHttp, JSON (Gson, org.json), client–server communication.

Database: SQLite (background DB ops, UI-responsive design).

UI/UX: Custom UI design, global font scaling, RecyclerView, Fragments, Activities.

System Features: Broadcasts, Services, custom notifications, custom ringtones, permission handling.

Performance: Background threading (Java threads) for smooth, non-blocking UI.`,
		},
		{
			heading: "Node.js / Express",
			content:
				`Core: REST API design, middleware usage, routing.

Authentication: Implemented Single Sign-On (SSO) with token-based auth (JWT/Bearer).

Data Handling: JSON-based request/response handling.

Integration: Backend communication with Android apps via secure APIs.

Basics: Error handling, request validation, environment setup.`,
		},
		{
			heading: "React",
			content:
				`Core: Functional components, hooks, state & props management.

Projects: Built personal portfolio website with React.

Client Work: Developing a photo studio site — image uploads, project showcase, customer connection via email (Mailgun integration).

UI/UX: Responsive layouts, reusable components, clean design practices.

Ecosystem: Familiar with React Router, basic API integration.`,
		},
		{
			heading: "C++ / DSA",
			content:
				`Data Structures: Linked lists, circular queues, binary trees.

Algorithms: Recursive solutions (e.g., Tower of Hanoi), number system conversions.

Systems Programming: Built a chat room using IPC with pthreads (handled up to 15 connections, scalable further).

Core Concepts: Pointers, memory management, object-oriented programming.

Concurrency: Familiar with multithreading and synchronization primitives.`,
		},
	];

	return (
		<section ref={ref} id="skills_section"
			className='h-full w-full grid grid-cols-1 sm:grid-cols-2 
	 p-5 px-15 bg-gray-700'>

			<div className="w-full sm:col-span-2 flex justify-center">
				<h2 className="h-fit text-center border-3 border-gray-300 rounded-2xl text-3xl p-3 font-bold text-white shadow-gray-50 hover:bg-gray-60"
					style={{ marginTop: `${navbar_height}px` }}>
					Skills
				</h2>
			</div>

			<div className="w-full text-white p-5 sm:text-lg text-center sm:text-left mt-10">
				“Over the past year, I’ve focused on mastering Android app development and building practical solutions.
				<br />My work includes designing user-friendly interfaces, implementing robust backend logic, and solving real-world problems such as offline data caching, performance optimization, and intuitive feature workflows.
				<br />I continuously experiment with modern architectures and libraries to improve app quality and maintainability, and I’m passionate about turning ideas into apps that users enjoy and rely on.”
			</div>

			<Timeline skills={skills} />

		</section >
	);
});

export default Skills;
