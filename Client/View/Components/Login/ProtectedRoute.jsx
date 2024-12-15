import {useContext} from "react";
import {ApplicationContext} from "../../../Application";
import {Navigate} from "react-router-dom";

const ProtectedRoute = ({ children }) => {

    const { user } = useContext(ApplicationContext);

    if (!user) {
        return <Navigate to="/" replace />;
    }

    return children;
}

export default ProtectedRoute;