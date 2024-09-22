import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./View/Login";
import './index.css'
import Register from "./View/Register";
import About from "./View/About";

const Application = () => {

    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Login />}/>
                <Route path="/register" element={<Register />}/>
                <Route path="/about" element={<About />}/>
            </Routes>
        </BrowserRouter>
    )
}

export default Application;