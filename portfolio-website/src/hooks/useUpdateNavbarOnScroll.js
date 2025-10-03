import { useEffect, useState } from "react";

function useUpdateNavbarOnScroll(refs = []) {

	console.log("inside custom hook")

	const [selected_option, set_selected_option] = useState("home_section");

	useEffect(() => {

		const observer = new IntersectionObserver((entries) => {

			const visibleEntry = entries
				.filter((entry) => entry.isIntersecting)
				.sort((a, b) => b.boundingClientRect.top - a.boundingClientRect.top)[0];

			if (visibleEntry) {
				set_selected_option(visibleEntry.target.id);
			}


		}, { threshold: 0.75 });

		refs.forEach((ref) => ref.current && observer.observe(ref.current));

		return (() => { refs.forEach(ref => ref.current && observer.unobserve(ref.current)); });

	}, []);

	useEffect(() => {
		console.log("selected_option changed:", selected_option);
	}, [selected_option]);;

	return [selected_option, set_selected_option];
}

export default useUpdateNavbarOnScroll;
