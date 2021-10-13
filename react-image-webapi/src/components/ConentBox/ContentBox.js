import React from 'react'
import './ContentBox.css'
import { Card } from 'antd';

const ContentBox = (props) => {

    let content = props.content;

    const displayContent = (elems) => {
        return elems.map((pair) => {
            return <div className="contentItem" key={pair.name}>
                {pair.name}<br/>{Number((pair.confidence).toFixed(1))}%
            </div>
        })
    }

    return (
        <div id="containerCard">
            {<Card title="">
                {displayContent(content)}
            </Card>}
        </div>)
}

export default ContentBox