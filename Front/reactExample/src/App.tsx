import { useEffect, useState } from 'react'
import './App.css'
import { WeatherCard } from './WeatherCard';

interface WeatherInfo {
  date: string,
  temperature: number
}

function App() {
  const [data, setData] = useState<WeatherInfo[]>()
  
  useEffect(() => {
    fetch("https://vm.nathoro.ru/weather?lattitude=54.3&longitude=48.4")
      .then(r => r.json())
      .then(d => setData(d))
  }, []);

  return (
    <>
      {data?.map(d => <WeatherCard key={d.date} date={d.date} temperature={d.temperature} />)}
    </>
  )
}

export default App
