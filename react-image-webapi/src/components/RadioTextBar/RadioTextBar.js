import { Radio } from 'antd';
import React, { useState, useEffect } from 'react'
import './RadioTextBar.css'

const RadioTextBar = (props) => {
  const [appState, setAppState] = useState(props.appState);

  const options = [
    { label: 'TAGS', value: 'TAGS' },
    { label: 'PERSONAL', value: 'PERSONAL' },
    { label: 'NSFW', value: 'NSFW' },
  ];

  const handleOptionClick = (event) => {
    setAppState((state) => {
      state.mode = event.target.value;
      return state
    });
  };

  useEffect(() => { console.log(appState.mode) }, [appState])

  return (
    <div>
      <Radio.Group className="testaa"
        options={options}
        onChange={handleOptionClick}
        optionType="button"
      />
    </div>
  );
}

export default RadioTextBar