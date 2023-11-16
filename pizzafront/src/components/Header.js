import Button from 'react-bootstrap/Button';
import { NavLink } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { faBasketShopping } from '@fortawesome/free-solid-svg-icons';
// import { faArrowRightFromBracket } from '@fortawesome/free-solid-svg-icons';
// import { useState, useEffect } from 'react';

function Header() {
//   const [flag, setFlag]=useState(0);
//   useEffect(()=>{
//     fetch(`http://localhost:8090/role/getFlag?login=maria`)
//     .then(response => response.json())
//     .then(res => setFlag(res))
// },[])

// const handleClick=(e)=>{
//   fetch(`http://localhost:8090/role/singOut?&login=maria`,{
//       method: "POST",
//       headers: {"Content-Type":"application/json"},
//       body:JSON.stringify()
//   }).then(()=>{
//     window.location.reload();
//   })
// }

  return (
    <header>
      <nav className="navbar navbar-expand-lg bg-body-tertiary">
        <div className="container">
           <div className='d-flex align-items-center'>
            <a className="navbar-brand" href="/">🍕 Пицка</a>
            <div className='ps-4'>
              <p className='m-0 fw-600'>Саратов</p>
              <p className='m-0 fw-600'>с 11:00 до 23:00</p>
            </div>
           </div>
           {/* {
            flag === 0 ? (<Button variant='secondary'><NavLink to={'/login'} className="fw-700"><FontAwesomeIcon className='pe-3' icon={faUser} /> Вход</NavLink></Button>) : 
            (<>
            <Button variant='secondary'><NavLink to={'/profile'} className="fw-700"><FontAwesomeIcon className='pe-3' icon={faUser} /> Профиль</NavLink></Button>
           <Button variant='secondary'><NavLink to={'/basket'} className="fw-700"><FontAwesomeIcon className='pe-3' icon={faBasketShopping}/>Корзина</NavLink></Button>
            <Button variant='secondary' className='text-black fw-600' onClick={()=>handleClick()}><FontAwesomeIcon className='pe-3' icon={faArrowRightFromBracket}/>Выйти</Button>
            </>)
           } */}
           <Button variant='secondary'><NavLink to={'/profile'} className="fw-700"><FontAwesomeIcon className='pe-3' icon={faUser} /> Профиль</NavLink></Button>
           <Button variant='secondary'><NavLink to={'/basket'} className="fw-700"><FontAwesomeIcon className='pe-3' icon={faBasketShopping}/>Корзина</NavLink></Button> 
          
        </div>
      </nav>
    </header>
  );
}

export default Header;


        
        
        