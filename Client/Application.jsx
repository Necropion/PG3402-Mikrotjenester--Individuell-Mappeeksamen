import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./View/Login";
import './index.css'
import Register from "./View/Register";
import About from "./View/About";
import Home from "./View/Home";
import { createContext, useState } from "react";

export const ApplicationContext = createContext();

const Application = () => {

    // Backend Access
    const gateway = process.env.REACT_APP_API_URL;

    const [user, setUser] = useState(null);
    const [cartList, setCartList] = useState(null);
    const [cartDeleted, setCartDeleted] = useState(false);
    const [cartSelected, setCartSelected] = useState(false);

    return(
        <ApplicationContext.Provider value={ {
            gateway,
            user, setUser,
            cartList, setCartList,
            cartDeleted, setCartDeleted,
            cartSelected, setCartSelected
        } }>
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