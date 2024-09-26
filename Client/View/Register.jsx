import Header from "./Components/Header";
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";

const Register = () => {

    const navigate = useNavigate()

    // Variables
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");

    // Events
    const [statusMsg, setStatusMsg] = useState("Create an account");

    const handleClick = async (e) => {
        e.preventDefault();

        if(e.target.id === "registerBtn") {
            if (!username || !password || !email) {
                setStatusMsg("Please fill in all fields!")
            } else {
                const newUser = await fetch('/api/user', {
                    method: "POST",
                    body: JSON.stringify({ username, password, email }),
                    headers: {
                        'Content-Type': 'application/json',
                    }
                })

                if(newUser.ok) {
                    setStatusMsg(`New user ${username} added!`)
                    setUsername("")
                    setPassword("")
                    setEmail("")
                }
            }

        } else if(e.target.id === "loginBtn") {
            navigate("/")
        }
    }

    return(
        <>
            <Header/>
            <main className={'w-[100vw] h-[90vh] flex flex-wrap justify-center content-center'}>
                <form className={'flex flex-wrap bg-white w-[30vw] h-[60vh] rounded-xl justify-center'}>
                    <h2 className={'mt-[20px] text-4xl w-[100%] text-center'}>Sign Up</h2>
                    <p className={'text-2xl w-[100%] h-[] text-center'}>{statusMsg}</p>
                    <input type="text"
                           className={'ml-[20%] mr-[20%] w-[60%] h-[50px] border-2 border-black text-center'}
                           placeholder="Username"
                           value={username}
                           onChange={(e) => {
                               setUsername(e.target.value)
                           }}/>
                    <input type="password"
                           className={'ml-[20%] w-[60%] mr-[20%] h-[50px] border-2 border-black text-center'}
                           placeholder="Password"
                           value={password}
                           onChange={(e) => {
                               setPassword(e.target.value)
                           }}/>
                    <input type="text"
                           className={'ml-[20%] w-[60%] mr-[20%] h-[50px] border-2 border-black text-center'}
                           placeholder="Email"
                           value={email}
                           onChange={(e) => {
                               setEmail(e.target.value)
                           }}/>
                    <button id="registerBtn" className={'ml-[42.5%] mr-[42.5%] w-[15%] h-[50px] bg-cyan-600 rounded-lg hover:bg-green-400'} onClick={handleClick}>Sign Up</button>
                    <p className={'text-xl mr-[5px] mt-[10px]'}>Already have an account?</p>
                    <button id="loginBtn" className={'w-[15%] h-[50px] bg-cyan-600 rounded-lg hover:bg-green-400'}
                            onClick={handleClick}>Login
                    </button>
                </form>
            </main>
        </>
    )
}

export default Register;