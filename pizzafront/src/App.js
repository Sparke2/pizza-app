import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import "@fontsource/nunito";
import "@fontsource/nunito/400.css";
import "@fontsource/nunito/400-italic.css";
import Assortment from './pages/Assortment';
import Users from './pages/Users';
import Orders from './pages/Orders';
import Home from './pages/Home';
import Login from './pages/Login';
import Profile from './pages/Profile';
import Basket from './pages/Basket';
import { Routes, Route } from "react-router-dom";


function App() {
  return (
    <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/admin/" element={<Assortment />} />
        <Route path="/admin/users" element={<Users />} />
        <Route path="/admin/orders" element={<Orders />} />
        <Route path='/basket' element={<Basket />} />
        {/* <Route path="/delivery" element={<Delivery />} />  */}
        <Route path="/profile" element={<Profile/>} />
        <Route path="/login" element={<Login />} />
        <Route path="*" element={<h1>Страница не найдена</h1>} />
    </Routes>
  );
}

export default App;
