# SickDay mobile app

## Features
![bar](.github/assets/img/panel.svg)

### Main screen 
The home screen displays real-time weather conditions for your location:     
- Temperature
- Atmospheric pressure
- Humidity
- Kp index (geomagnetic activity level)

### Weather calendar
Browse weather data by date using an interactive calendar:
- Tap any date to select it
- The weather widget below the calendar updates to show conditions for the selected day (using the same weather component as the home screen)

### Health notes
Track your wellbeing with a dedicated notes screen:
- **Health state slider** — rate your overall condition
- **Free-text note** — jot down additional details
- **Symptom sliders** — log the severity of specific symptoms:
    - Drowsiness
    - Pressure (headache/blood pressure sensation)
    - Weakness

## Tech stack

**Language:** Kotlin    
**UI framework** Jetpack Compose    
**ORM:** Room3    
**DI framework:** Koin    
**Network framework:** Ktor   
**Build system:** Gradle   
**Min sdk:** 31

## API`s

### Weather — [open-meteo](https://open-meteo.com/)
Open-Source weather API with free access for non-commercial use.
No API Key required. Accurate weather forecasts for any location. 
Used for fetching pressure, temperature and humidity.
### Kp index — [NOAA SWPC](https://www.swpc.noaa.gov/products/planetary-k-index)
Kp index updates every 3 hours.

## Screenshots