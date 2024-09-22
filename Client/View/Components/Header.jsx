import {Link} from "react-router-dom";

const Header = () => {

    return (
        <header className={'bg-white w-[100vw] h-[10vh] flex flex-wrap justify-center content-center'}>
            <div className={'w-[33vw]'}></div>
            <h2 className={'text-4xl text-center w-[33vw]'}>Vehicle Shop</h2>
            <div className={'w-[31vw] mr-[2vw] flex flex-wrap justify-end'}>
                <Link className={'border-b-2 border-cyan-600 hover:border-black ml-5'} to={"/"}>Login</Link>
                <Link className={'border-b-2 border-cyan-600 hover:border-black ml-5'} to={"/about"}>About Us</Link>
            </div>
        </header>
    )
}

export default Header;