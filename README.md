# Приложение Strunner
Мобильный клиент сервиса [Strava](https://www.strava.com/)

### Реализованный функционал
- при первом запуске пользователю предлагается onboarding
- и возможность OAuth-авторизоваться
- на главном экране - список его тренировок
- на втором экране - детальная информация о тренировке
- offline режим
- два языка - русский и английский
- ландшафтная и портретная ориентации

### Технологии
`Single-activity` `MVVM` `Room` `Navigation Component` `Retrofit`
`coroutines` `Glide` `SharedPreferences` `PaginationScrollListener`
`Deeplink` `ViewPager2` `AutoClearedValue` `ViewBinding`

![Strunner_demo](https://user-images.githubusercontent.com/60923967/156423578-fbee07a2-d1bb-4c2e-8fab-60fae90084c9.gif)

*после закрытия приложения видео повторяется

**при повторном запуске onboarding не показывается и
авторизоваться не нужно (токен сохраняется в SharedPreferences)

[Общее описание API](https://developers.strava.com/docs/reference/)
