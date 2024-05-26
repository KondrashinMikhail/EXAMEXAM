export const WeatherCard = (props: {date: string, temperature: number}) => {
    return <>
        <div>
            <div>Date: { new Date(props.date).toLocaleDateString()}</div> 
            <div>Temperature: {props.temperature.toFixed(1)}</div>
        </div>
    </>;
}