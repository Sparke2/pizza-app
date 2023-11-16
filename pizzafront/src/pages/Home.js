import { useEffect, useState } from 'react';
import Header from "../components/Header";
import Footer from "../components/Footer";
import img from "../img/pizza_1.png"
import Filters from '../components/Filter';
import { Button } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';

function Home() {
const [pizza, setPizza]=useState('');
const [searchPizzaType, setSearchPizzaType] = useState({name: '', type: 0});
const [searchPizza, setSearchPizza] = useState('Укажите название пиццы');
let searchPizzaT = '';
if (searchPizzaType.type !== 0 && searchPizzaType.type !== 4) {
  searchPizzaT = `/findByCategory?category=${searchPizzaType.name}`
} else if (searchPizzaType.type === 4){
  searchPizzaT = `/findByPopularity`
} else {
  searchPizzaT = ''
}
const searchPizzaVale = (searchPizza !== '' && searchPizza !== 'Укажите название пиццы'? `/findByName?name=${searchPizza}` : '');

useEffect(()=>{
    fetch(`http://localhost:8090/pizza${searchPizzaT}`)
    .then(response => response.json())
    .then(res => setPizza(res))
},[searchPizzaType])

useEffect(()=>{
  fetch(`http://localhost:8090/pizza${searchPizzaVale}`)
  .then(response => response.json())
  .then(res => setPizza(res))
},[searchPizza])

const updatePizza = id => {
  console.log(id);
  fetch("http://localhost:8090/pizza/setIsBasket/"+ id, {
    method: "PUT",
    headers: {"Content-Type":"application/json"},
    body:JSON.stringify(id)
  }).then(()=>{
    window.location.reload();
  })
}

  return (
    <>
    <Header/>
    <div className="container py-5 g-4">
        <h3 className="fw-700 pb-4">Пицца</h3>
        <div className='row'>
          <div className='col'>
            <Filters value={searchPizzaType} onChange={(i) => setSearchPizzaType(i)} />
          </div>
          <div className='col'>
            <div className="input-group mb-3">
              <input type="text" className="form-control" value={searchPizza} onChange={(event) => setSearchPizza(event.target.value)} />
              <div className="input-group-append">
                <Button variant='outline-secondary'>
                  <FontAwesomeIcon icon={faMagnifyingGlass} bounce />
                </Button>
              </div>
            </div>
          </div>
        </div>
        <div className="row row-cols-4">
        {Array.isArray(pizza) ? pizza.map((p, index)=>(
            <div className="col" key={index}>
              <div className='card'>
                <img src={img} className="card-img-top" alt="Избражение пиццы"/>
                <div className="card-body">
                  <h5 className="card-title fw-700">{p.name}</h5>
                  <p className="card-text">{p.description}</p>
                  <div className="d-flex justify-content-between align-items-center">
                    <p className="fw-700 m-0">{p.price} ₽</p>
                    <button className="btn btn-primary" onClick={()=>updatePizza(p.id)}>Хочу</button>
                  </div>
                </div>
              </div>
            </div>
             )) : null}
        </div>
    </div>
    <Footer/>
    </>
  );
}

export default Home;