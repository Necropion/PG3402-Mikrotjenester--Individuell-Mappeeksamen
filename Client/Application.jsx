import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./View/Login";
import './index.css'
import Register from "./View/Register";
import About from "./View/About";
import Home from "./View/Home";
import { createContext, useState } from "react";
import ProtectedRoute from "./View/Components/Login/ProtectedRoute";
import Checkout from "./View/Checkout";
import Profile from "./View/Profile";

export const ApplicationContext = createContext();

const Application = () => {

    // Backend Access
    const gateway = process.env.REACT_APP_API_URL;

    // Persisting variables for app context
    const [user, setUser] = useState(() => {
        const storedUser = localStorage.getItem("user");
        return storedUser ? JSON.parse(storedUser) : null
    });

    const [cartList, setCartList] = useState(() => {
        const storedCartList =  localStorage.getItem("cartList");
        return storedCartList ? JSON.parse(storedCartList) : null
    });

    const [cart, setCart] = useState(() => {
        const storedCart = localStorage.getItem("cart");
        return storedCart ? JSON.parse(storedCart) : null
    });

    const [receipt, setReceipt] = useState(() => {
        const storedReceipt = localStorage.getItem("receipt");
        return storedReceipt ? JSON.parse(storedReceipt) : null
    });

    const [cartDeleted, setCartDeleted] = useState(false);

    return(
        <ApplicationContext.Provider value={ {
            gateway,
            user, setUser,
            cartList, setCartList,
            cart, setCart,
            cartDeleted, setCartDeleted,
            receipt, setReceipt
        } }>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Login />}/>
                    <Route path="/register" element={<Register />}/>
                    <Route path="/about" element={<About />}/>
                    <Route path="/home" element={<ProtectedRoute><Home /></ProtectedRoute>}/>
                    <Route path="/checkout" element={<ProtectedRoute><Checkout/></ProtectedRoute>}/>
                    <Route path="/profile" element={<ProtectedRoute><Profile/></ProtectedRoute>}/>
                </Routes>
            </BrowserRouter>
        </ApplicationContext.Provider>
    )
}

export default Application;