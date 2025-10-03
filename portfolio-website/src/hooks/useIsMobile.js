import { useEffect, useState } from "react";

const breakpoint = 768;

function useIsMobile() {

	const [isMobile, setIsMobile] = useState(
		() => typeof window != "undefined" ? (window.innerWidth < breakpoint) : true
	);

	useEffect(() => {
		const handleWidthChange = () => {
			setIsMobile(window.innerWidth < breakpoint);
		}
		window.addEventListener("resize", handleWidthChange);
		return (() => window.removeEventListener("resize", handleWidthChange))
	});

	return isMobile;
}

export default useIsMobile;
