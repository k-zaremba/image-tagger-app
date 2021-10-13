import React, { useState, useEffect } from 'react'
import { Button } from 'antd';
import './ResponseBox.css'
import ContentBox from '../ConentBox/ContentBox';

const ResponseBox = (props) => {
  const [loadAnimation, setLoadAnimation] = useState(false);
  const [dataFetched, setDataFetched] = useState(true);
  const [contentLoaded, setContentLoaded] = useState(false);
  const [appState, setAppState] = useState(props.appState);

  const enterLoading = () => {
    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      body: JSON.stringify({ url: appState.url, mode: appState.mode })
    };

    setDataFetched(false);
    setContentLoaded(false);
    fetch('http://localhost:8080/images/tag', requestOptions)
      .then(res => res.json())
      .then((res) => {
        let resStripped;
        if (res.result != undefined) {
          resStripped = res.result.tags.map(
            (ele) => {
              return {
                confidence: ele.confidence,
                name: ele.tag.en
              }
            });
        }
        else{
            resStripped = [{
              confidence : 4,
              name: "BAD REQUEST"
            },
            {
              confidence : 0,
              name: "CHOOSE MODE"
            },
            {
              confidence : 0,
              name: "BAD REQUEST"
            }]
          }
        
        setAppState((state) => {
          state.response = resStripped;
          return state
        });
        setDataFetched(true);
        setContentLoaded(true);
      })
  };

  useEffect(() => {
    if (dataFetched)
      setLoadAnimation(false);
    else
      setLoadAnimation(true);
  }, [dataFetched])

  return (
    <div>
      <Button shape='round'
        loading={loadAnimation}
        onClick={() => enterLoading()}>
        PROCESS!
      </Button>
      {contentLoaded && <ContentBox content={appState.response} />}
    </div>
  )
}

export default ResponseBox;