import { forwardRef } from "react";
import portfolio_logo from "./../assets/portfolio_logo_light.svg"

const Navbar = forwardRef(({ }, ref) => {

	return (
		<header ref={ref} className="sticky top-0 inset-x-0 flex md:justify-start md:flex-nowrap z-50 w-full text-sm px-2">
			<nav className="mt-4 relative max-w-2xl w-full  border  rounded-[24px] mx-2 
		flex md:flex-nowrap items-center justify-between
		p-1 ps-4 md:py-0 sm:mx-auto bg-gray-900 border-gray-900">
				<div className="flex items-center">
					{/*Logo*/}
					<img src={portfolio_logo} alt="logo" className="text-white w-10 h-10" />
					{/* End Logo */}
					<div className="ms-1 sm:ms-2" />

				</div>

				<div id="hs-navbar-header-floating"
					className=" hs-collapse overflow-hidden px-5
					transition-all duration-300 basis-full grow md:block"
					aria-labelledby="hs-navbar-header-floating-collapse">

					<div className="flex flex-row items-center justify-end
		gap-1 md:gap-3 mt-3 md:mt-0 py-0 md:ps-7">

						<a className='py-1 md:py-3 px-2 sm:px-4 font-medium 
				focus:outline-hidden border-neutral-200 text-neutral-200 
				transition duration-300 ease-in-out
				hover:text-white hover:scale-110'
							href="#">
							Home
						</a>

						<a className='py-1 md:py-3 px-2 sm:px-4 font-medium 
				focus:outline-hidden border-neutral-200 text-neutral-200 
				transition duration-300 ease-in-out
				hover:text-white hover:scale-110'
							href="#skills_section">
							Skills
						</a>

						<a className='py-1 md:py-3 px-2 sm:px-4 font-medium 
				focus:outline-hidden border-neutral-200 text-neutral-200 
				transition duration-300 ease-in-out
				hover:text-whiten hover:scale-110'
							href="#projects_section">
							Projects
						</a>

					</div>

				</div>
			</nav>
		</header>
	);
});

export default Navbar;
