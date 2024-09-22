import Header from "./Components/Header";
import {useContext, useState} from "react";
import {ApplicationContext} from "../Application";

const Home = () => {

    const { user } = useContext(ApplicationContext);

    return(
        <>
            <Header />
            <main className={'text-center text-8xl mt-[30vh] flex flex-wrap justify-center'}>
                <div className={'bg-white rounded-xl w-[72vw]'}>

                </div>
                <div className={'bg-white rounded-xl ml-[1vw] w-[25vw]'}>
                    Welcome, {user.username}
                </div>
            </main>
        </>
    )
}

export default Home;