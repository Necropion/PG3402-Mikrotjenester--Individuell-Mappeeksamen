import {Link, useNavigate} from "react-router-dom";
import man_profile_icon from "../../Images/man-profile-icon.svg"
import {useContext} from "react";
import {ApplicationContext} from "../../Application";

const Header = () => {

    const { user, setUser } = useContext(ApplicationContext);
    const navigate = useNavigate();

    const handleLogout = () => {
        setUser(null)
        navigate("/")
    }

    return (
        <header className={'bg-white w-[100vw] h-[10vh] flex flex-wrap justify-center content-center'}>
            {user ? <div className={'w-[33vw] flex flex-wrap'}>
                <img src={man_profile_icon} alt="man_profile_icon" className={'w-[50px] h-[50px]'}/>
                <p className={'mt-4 ml-2'}>Name: {user.username}</p>
                <p className={'mt-4 ml-2'}>Email: {user.email}</p>
            </div> : <div className={'w-[33vw]'}> </div>}
            <h2 className={'text-4xl text-center w-[33vw]'}>Vehicle Shop</h2>
            <div className={'w-[31vw] mr-[2vw] flex flex-wrap justify-end'}>
                {user ? <div className={'hover:border-b-2 hover:border-cyan-600 ml-5 mt-4 h-[35]'} onClick={handleLogout}>Log out</div>
                  :  <Link className={'hover:border-b-2 hover:border-cyan-600 ml-5 mt-4 h-[35]'} to={"/"}>Login</Link>}
                <Link className={'hover:border-b-2 hover:border-cyan-600 ml-5 mt-4 h-[35]'} to={"/register"}>Register</Link>
                <Link className={'hover:border-b-2 hover:border-cyan-600 ml-5 mt-4 h-[35]'} to={"/home"}>Home</Link>
                <Link className={'hover:border-b-2 hover:border-cyan-600 ml-5 mt-4 h-[35]'} to={"/about"}>About Us</Link>
            </div>
        </header>
    )
}

export default Header;