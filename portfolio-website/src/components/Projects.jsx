import { forwardRef } from "react";
import Timeline from "./Timeline";

const Projects = forwardRef(({ navbar_height }, ref) => {

	const projects = [
		{
			time: "12/02/2025",
			heading: "Android",
			content: ``,
		},
		{
			time: "14/02/2025",
			heading: "Kickoff_2",
			content: "_2Lorem ipsum dolor sit ame_2Lorem ipsum dolor sit amet consectetur adipisicing elit.Fuga officiis tempora ipsum adipisci tenetur sunt quae exercitationem sed pariatur porro!t consectetur adipisicing elit.Fuga officiis tempora ipsum adipisci tenetur sunt quae exercitationem sed pariatur porro!",
		},
		{
			time: "14/02/2025",
			heading: "Kickoff_2",
			content: "_2Lorem ipsum _2Lorem ipsum dolor sit amet consectetur adipisicing elit.Fuga officiis tempora ipsum adipisci tenetur sunt quae exercitationem sed pariatur porro!_2Lorem ipsum dolor sit amet consectetur adipisicing elit.Fuga officiis tempora ipsum adipisci tenetur sunt quae exercitationem sed pariatur porro!dolor sit amet consectetur adipisicing elit.Fuga officiis tempora ipsum adipisci tenetur sunt quae exercitationem sed pariatur porro!",
		},
		{
			time: "14/02/2025",
			heading: "Kickoff_2",
			content: "__2Lorem ipsum dolor sit amet consectetur adipisicing elit.Fuga officiis tempora ipsum adipisci tenetur sunt quae exercitationem sed pariatur porro!_2Lorem ipsum dolor sit amet consectetur adipisicing elit.Fuga officiis tempora ipsum adipisci tenetur sunt quae exercitationem sed pariatur porro!2Lorem ipsum dolor sit amet consectetur adipisicing elit.Fuga officiis tempora ipsum adipisci tenetur sunt quae exercitationem sed pariatur porro!",
		},
		{
			time: "14/02/2025",
			heading: "Kickoff_2",
			content: "__2Lorem ipsum dolor sit amet consectetur adipisicing elit.Fuga officiis tempora ipsum adipisci tenetur sunt quae exercitationem sed pariatur porro!_2Lorem ipsum dolor sit amet consectetur adipisicing elit.Fuga officiis tempora ipsum adipisci tenetur sunt quae exercitationem sed pariatur porro!2Lorem ipsum dolor sit amet consectetur adipisicing elit.Fuga officiis tempora ipsum adipisci tenetur sunt quae exercitationem sed pariatur porro!",
		},
		{
			time: "14/02/2025",
			heading: "Kickoff_2",
			content: "__2Lorem ipsum dolor sit amet consectetur adipisicing elit.Fuga officiis tempora ipsum adipisci tenetur sunt quae exercitationem sed pariatur porro!_2Lorem ipsum dolor sit amet consectetur adipisicing elit.Fuga officiis tempora ipsum adipisci tenetur sunt quae exercitationem sed pariatur porro!2Lorem ipsum dolor sit amet consectetur adipisicing elit.Fuga officiis tempora ipsum adipisci tenetur sunt quae exercitationem sed pariatur porro!",
		},
	];

	return (
		<section ref={ref} id="projects_section" className="h-full w-full grid grid-cols-1 sm:grid-cols-2 
	 p-5 px-15 bg-gray-800">

			<div className="w-full sm:col-span-2 flex justify-center">
				<h2 className="h-fit text-center border-3 border-gray-300 rounded-2xl text-3xl p-3 font-bold text-white shadow-gray-50 hover:bg-gray-600"
					style={{ marginTop: `${navbar_height}px` }}>
					Projects
				</h2>
			</div>

			<div className="w-full text-white p-5 sm:text-lg text-center sm:text-left mt-10">
				“Over the past year, I’ve focused on mastering Android app development and building practical solutions.
				<br />My work includes designing user-friendly interfaces, implementing robust backend logic, and solving real-world problems such as offline data caching, performance optimization, and intuitive feature workflows.
				<br />I continuously experiment with modern architectures and libraries to improve app quality and maintainability, and I’m passionate about turning ideas into apps that users enjoy and rely on.”
			</div>

			<Timeline projects={projects} />

		</section >
	);
});

export default Projects;
