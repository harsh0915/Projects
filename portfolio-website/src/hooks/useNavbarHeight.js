import { useEffect, useState } from "react";

function useNavbarHeight(ref) {

	const [h, setH] = useState(0);

	useEffect(() => {

		setH(ref.current.offsetHeight);

	}, [h]);

	return h;
}

export default useNavbarHeight;
