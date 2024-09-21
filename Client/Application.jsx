import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./View/Login";

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