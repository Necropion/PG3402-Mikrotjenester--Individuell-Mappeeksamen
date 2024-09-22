import {useState} from "react";
import eyeOn from '../Images/eye-on.svg'
import eyeOff from '../Images/eye-off.svg'
import Header from "./Components/Header";

const Login = () => {

    // Variables
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    // Events
    const[visInput, setVisInput] = useState("password")
    const[visImg, setVisImg] = useState(eyeOn)

    const handleClick = (e) => {
        e.preventDefault();

        if(e.target.id === "visBtn" || e.target.id === "visImg") {
            if (visInput === "password") {
                setVisInput("text")
                setVisImg(eyeOff)
            } else {
                setVisInput("password")
                setVisImg(eyeOn)
            }
        }
    }

    return(
        <>
            <Header />
            <main className={'w-[100vw] h-[90vh] flex flex-wrap justify-center content-center'}>
                <form className={'flex flex-wrap bg-white w-[30vw] h-[60vh] rounded-xl justify-center'}>
                    <h2 className={'mt-[20px] text-4xl w-[100%] text-center'}>Login</h2>
                    <p className={'text-2xl w-[100%] h-[] text-center'}>Please sign in to proceed</p>
                    <input type="text"
                           className={'ml-[20%] mr-[20%] w-[60%] h-[50px] border-2 border-black text-center'}
                           placeholder="Username"
                           value={username}
                           onChange={(e) => {setUsername(e.target.value)}}/>
                    <input type={visInput}
                           className={'ml-[20%] w-[60%] h-[50px] border-2 border-black text-center'}
                           placeholder="Password"
                           value={password}
                           onChange={(e) => {setPassword(e.target.value)}}/>
                    <button id="visBtn" className={'mr-[10%] w-[10%] h-[50px] bg-cyan-300 rounded-xl flex flex-wrap justify-center content-center'} onClick={handleClick}>
                        <img id="visImg" className={'w-[35px] h-[35px]'} src={visImg} onClick={handleClick} alt="eye-off"/>
                    </button>
                    <button className={'ml-[42.5%] mr-[42.5%] w-[15%] h-[50px] bg-cyan-300 rounded-xl'}>Sign In</button>
                    <p className={'text-xl mr-[5px] mt-[10px]'}>No account?</p>
                    <button className={'w-[15%] h-[50px] bg-cyan-300 rounded-xl'}>Register</button>
                </form>
            </main>
        </>
    )
}

export default Login;