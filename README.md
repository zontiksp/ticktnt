# TntTick 🚀

Профессиональный ванильный плагин для Minecraft, который рассчитывает точное время до взрыва динамита в реальном времени и предоставляет инструменты для администрирования.

## 🌟 Основной функционал

* **Плавный таймер:** Отображает над летящим ТНТ точное время до взрыва (с точностью до 0.1 секунды) в красивом цветном формате.
* **Автоматическое логирование:** Записывает каждый взвод ТНТ в файл `tnt-log.txt` с указанием точной даты, времени, игрового мира, координат взрыва и конкретного активатора (Игрок / Раздатчик / Взрыв от другого ТНТ).
* **Автоматический ZIP-архиватор:** При каждом перезапуске плагина или старте сервера старый текстовый лог мгновенно упаковывается в `.zip` архив с уникальной временной меткой в папку `logs`, освобождая место под новые записи.
* **Управление из консоли:** Специальная скрытая команда `/tntconsole toggle`, доступная строго для консоли сервера, которая позволяет на лету включать или выключать спам логов в общее окно консоли, при этом полностью сохраняя запись в локальные файлы плагина.

---

# TntTick 🚀

A professional vanilla Minecraft plugin that calculates the exact time until TNT detonates in real-time and provides powerful administrative logging tools.

## 🌟 Key Features

* **Smooth Countdown Timer:** Displays a precise countdown (accurate to 0.1 seconds) directly above the primed TNT entity using clean, customizable color formats.
* **Automatic Logging:** Logs every TNT ignition event into a local `tnt-log.txt` file, recording the exact timestamp, world name, explosion coordinates, and the specific activator (Player / Dispenser / Chain reaction from another TNT).
* **Automated ZIP Archiver:** Upon every plugin reload or server startup, the old text log file is instantly compressed into a unique timestamped `.zip` archive inside the `logs` folder to optimize disk space.
* **Console-Only Control:** Features a hidden `/tntconsole toggle` command accessible strictly from the server console. It allows administrators to enable or disable live console spam on the fly while ensuring local file logging remains completely uninterrupted.
