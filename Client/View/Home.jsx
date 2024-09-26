import Header from "./Components/Header";
import {useContext, useEffect, useState} from "react";
import {ApplicationContext} from "../Application";

const Home = () => {

    const { user } = useContext(ApplicationContext);

    // Car Variables
    const [carList, setCarList] = useState([])

    const fetchCars = async () => {
        const getAllCars = await fetch('/api/car')
        const allCars = await getAllCars.json();

        if(getAllCars.ok) {
            setCarList(allCars)
        }
    }

    useEffect(() => {
        fetchCars();
    }, []);

    return(
        <>
            <Header />
            <main className={'text-center text-8xl mt-[5vh] flex flex-wrap justify-center'}>
                <div className={'bg-white rounded-xl w-[72vw] grid grid-cols-4'}>
                    {carList.map((car, index) => (
                        <div className={'text-xl bg-black text-white ml-2 mt-2'}>{car.make}, {car.model}, {car.color} ({car.carYear})</div>
                    ))}
                </div>
                <div className={'bg-white rounded-xl ml-[1vw] w-[25vw]'}>
                    Welcome, {user.username}
                </div>
            </main>
        </>
    )
}

export default Home;