function Timeline({ skills, projects }) {

	return (

		<ol
			className="relative space-y-8 before:absolute before:-ml-px before:h-full before:w-0.5 before:rounded-full before:bg-gray-400 mt-10">

			{projects && projects.map((e) => (
				<li className="relative -ms-1.5 flex items-start gap-4">
					<span className="size-3 shrink-0 rounded-full bg-indigo-500"></span>

					<div className="-mt-2">

						<time className="text-sm/none font-medium text-gray-700 dark:text-gray-200">{e.time}</time>

						<h3 className="text-lg font-bold text-gray-900 dark:text-white">{e.heading}</h3>

						<p className="mt-0.5 text-md text-gray-700 dark:text-gray-200">
							{e.content}
						</p>

					</div>
				</li>
			))}

			{skills && skills.map((e) => (
				<li className="relative -ms-1.5 flex items-start gap-4">
					<span className="size-3 shrink-0 rounded-full bg-indigo-500"></span>

					<div className="-mt-2">

						<h3 className="text-lg font-bold text-gray-900 dark:text-white">{e.heading}</h3>

						<p className="mt-0.5 text-md text-gray-700 dark:text-gray-200">
							{e.content}
						</p>

					</div>
				</li>
			))}

		</ol>
	);
};

export default Timeline;
