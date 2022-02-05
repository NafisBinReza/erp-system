import React, { useState } from 'react';
import Select from 'react-select';

const options = [
    { value: 'chocolate', label: 'Chocolate' },
    { value: 'strawberry', label: 'Strawberry' },
    { value: 'vanilla', label: 'Vanilla' },
];

export default function About() {
    const [selectedOption, setSelectedOption] = useState("Nothing");
    const a = selectedOption;

    return (
        <div className="App">
            <Select
                defaultValue={selectedOption}
                onChange={setSelectedOption}
                options={options}
            />
            <h4>{a}</h4>
        </div>
    );
}

