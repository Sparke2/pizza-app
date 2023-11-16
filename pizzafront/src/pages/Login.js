import { useState, useEffect } from "react";
import { Button } from "react-bootstrap";
import { NavLink } from "react-router-dom";

function Login() {
    const [login, setLogin]=useState('');
    const [password, setPassword]=useState('');


    const handleClick=(e)=>{
      fetch(`http://localhost:8090/role/singIn?password=${password}&login=${login}`,{
          method: "POST",
          headers: {"Content-Type":"application/json"},
          body:JSON.stringify()
      }).then(()=>{
        window.location.reload();
      })
    }

    return (
     <div className="d-flex align-item-center justify-content-center">
       <div className="m-5 p-5 br-2 br-5">
         <p className="fs-30 fw-700">Вход на сайт</p>
            <label htmlFor="login" className="form-label">Логин</label>
            <input value={login} className="form-control" id="login" onChange={e => setLogin(e.target.value)} />
            <label htmlFor="password" className="form-label">Пароль</label>
            <input value={password} type="text" className="form-control" id="password" required onChange={e => setPassword(e.target.value)} />
            <NavLink to="/" end className="text-white"><Button className="mt-5 w-100" onClick={() => handleClick()}>Вход</Button></NavLink>
       </div>
     </div>
    );
  }
  
export default Login;