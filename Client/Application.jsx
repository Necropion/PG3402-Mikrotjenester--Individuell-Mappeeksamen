import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./View/Login";
import './index.css'

const Application = () => {

    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Login />}/>
            </Routes>
        </BrowserRouter>
    )
}

export default Application;