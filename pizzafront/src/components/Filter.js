import { useState } from "react";

function Filters({value, onChange}) {
    const categories = [
        {name: 'Все', type: 0},
        {name: 'Грибная', type: 1},
        {name: 'Мясная', type: 2},
        {name: 'Острая', type: 3},
        {name: 'По популярности', type: 4},
      ];

    const [activeSpan, setActiveSpan] = useState(value.type);

    const toggleSort = (i) => {
        onChange(i);
        setActiveSpan(i.type)
    }
    return (
        <>
       
        <div className="filter">
            <div className="filters d-flex">
                {
                    categories.map((obj, i) => <span key={i} className = {activeSpan === i ? "active" : ""} onClick={() => toggleSort(obj)}>{obj.name}</span>)
                }
            </div>
          </div>
          </>
    );
}

export default Filters;