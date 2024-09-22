import {Link} from "react-router-dom";
import man_profile_icon from "../../Images/man-profile-icon.svg"
import {useContext} from "react";
import {ApplicationContext} from "../../Application";

const Header = () => {

    const { user } = useContext(ApplicationContext);

    return (
        <header className={'bg-white w-[100vw] h-[10vh] flex flex-wrap justify-center content-center'}>
            <div className={'w-[33vw] flex flex-wrap'}>
                <img src={man_profile_icon} alt="man_profile_icon" className={'w-[50px] h-[50px]'}/>
                <p className={'mt-4 ml-2'}>Name: {user.username}</p>
                <p className={'mt-4 ml-2'}>Email: {user.email}</p>
            </div>
            <h2 className={'text-4xl text-center w-[33vw]'}>Vehicle Shop</h2>
            <div className={'w-[31vw] mr-[2vw] flex flex-wrap justify-end'}>
                <Link className={'border-b-2 border-cyan-600 hover:border-black ml-5'} to={"/"}>Login</Link>
                <Link className={'border-b-2 border-cyan-600 hover:border-black ml-5'} to={"/register"}>Register</Link>
                <Link className={'border-b-2 border-cyan-600 hover:border-black ml-5'} to={"/home"}>Home</Link>
                <Link className={'border-b-2 border-cyan-600 hover:border-black ml-5'} to={"/about"}>About Us</Link>
            </div>
        </header>
    )
}

export default Header;