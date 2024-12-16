import {useContext, useState} from "react";
import eyeOn from '../Images/eye-on.svg'
import eyeOff from '../Images/eye-off.svg'
import Header from "./Components/Header";
import {useNavigate} from "react-router-dom";
import { ApplicationContext } from "../Application";

const Login = () => {

    const navigate = useNavigate()
    const { gateway, setUser } = useContext(ApplicationContext)

    // User Variables
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    // Events
    const [statusMsg, setStatusMsg] = useState("Please sign in to proceed");
    const [visInput, setVisInput] = useState("password")
    const [visImg, setVisImg] = useState(eyeOn)


    const fetchUser = async (userId) => {
        try {
            const getUser = await fetch(`${gateway}/api/user/${userId}`)

            if (getUser.ok) {
                const user = await getUser.json();
                setUser(user);
                localStorage.setItem("user", JSON.stringify(user));
            }
        } catch (error) {
            setStatusMsg("Error while fetching user data")
            console.error("Error fetching user:", error);
        }
    }

    const handleClick = async (e) => {
        e.preventDefault();

        // Password Vision Toggle Button
        if(e.target.id === "visBtn" || e.target.id === "visImg") {
            if (visInput === "password") {
                setVisInput("text")
                setVisImg(eyeOff)
            } else {
                setVisInput("password")
                setVisImg(eyeOn)
            }
        // Login Auth Fetch
        } else if(e.target.id === "loginBtn") {

            if (!username || !password) {
                setStatusMsg("Please fill in all fields!")
            } else {
                try {
                    const userAuth = await fetch(`${gateway}/api/user/auth?username=${username}&password=${password}`)

                    if (userAuth.ok) {
                        const userFound = await userAuth.json();

                        console.log(userFound)

                        if (userFound.authentication === true) {
                            await fetchUser(userFound.userId);
                            navigate("home")
                        }

                        setStatusMsg("The username/password was incorrect")
                    }
                } catch (error) {
                    setStatusMsg("Network error or server is unavailable.");
                    console.error("Error authenticating:", error);
                }
            }

        } else if(e.target.id === "registerBtn") {
            navigate("/register")
        }
    }

    return(
        <>
            <Header />
            <main className={'w-[100vw] h-[90vh] flex flex-wrap justify-center content-center'}>
                <form className={'flex flex-wrap bg-white w-[30vw] h-[60vh] rounded-xl justify-center'}>
                    <h2 className={'mt-[20px] text-4xl w-[100%] text-center'}>Login</h2>
                    <p className={'text-2xl w-[100%] h-[] text-center'}>{statusMsg}</p>
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
                    <button id="visBtn" className={'ml-[1%] mr-[10%] w-[9%] h-[50px] bg-cyan-600 rounded-lg flex flex-wrap justify-center content-center hover:bg-green-400'} onClick={handleClick}>
                        <img id="visImg" className={'w-[35px] h-[35px]'} src={visImg} onClick={handleClick} alt="eye-off"/>
                    </button>
                    <button id="loginBtn" className={'ml-[42.5%] mr-[42.5%] w-[15%] h-[50px] bg-cyan-600 rounded-lg hover:bg-green-400'} onClick={handleClick}>Login</button>
                    <p className={'text-xl mr-[5px] mt-[10px]'}>No account?</p>
                    <button id="registerBtn" className={'w-[15%] h-[50px] bg-cyan-600 rounded-lg hover:bg-green-400'} onClick={handleClick}>Sign Up</button>
                </form>
            </main>
        </>
    )
}

export default Login;