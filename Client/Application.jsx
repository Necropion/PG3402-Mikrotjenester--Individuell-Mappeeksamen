import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./View/Login";
import './index.css'
import Register from "./View/Register";
import About from "./View/About";
import Home from "./View/Home";
import { createContext, useState } from "react";

export const ApplicationContext = createContext();

const Application = () => {

    const [user, setUser] = useState({})

    return(
        <ApplicationContext.Provider value={ { user, setUser } }>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Login />}/>
                    <Route path="/register" element={<Register />}/>
                    <Route path="/about" element={<About />}/>
                    <Route path="/home" element={<Home />}/>
                </Routes>
            </BrowserRouter>
        </ApplicationContext.Provider>
    )
}

export default Application;