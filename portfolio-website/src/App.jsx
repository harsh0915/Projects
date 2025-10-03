import Footer from "./components/Footer";
import LandingPage from "./components/LandingPage";
import Navbar from "./components/Navbar";
import Skills from "./components/Skills";
import Projects from "./components/Projects";
import MobileContext from "./contexts/MobileContext";
import useIsMobile from "./hooks/useIsMobile";
import { useRef } from "react";
import useNavbarHeight from "./hooks/useNavbarHeight";

function App() {

	const isMobile = useIsMobile();

	const navbar = useRef(null);
	const home = useRef(null);
	const skills = useRef(null);
	const projects = useRef(null);

	const navbar_height = useNavbarHeight(navbar);

	return (
		<MobileContext.Provider value={isMobile}>

			<div className="bg-gray-300">
				<Navbar ref={navbar} />
				<LandingPage ref={home} />
				<Skills ref={skills} navbar_height={navbar_height} />
				<Projects ref={projects} navbar_height={navbar_height} />
				<Footer />
			</div>

		</MobileContext.Provider>
	)
}

export default App
