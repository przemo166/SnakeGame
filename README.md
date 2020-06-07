# Snake

<p align="center">
  <img width="533" height="300" src="https://github.com/przemo166/SnakeGame/blob/Threads/github_images/new_gif.gif">
</p>

# Informacja o programie

|  Nr.   |   | |
| :------------: | :------------: | :------------: |
| 1 | Autorzy  | Przemysław Malec, Przemysław Widz|
| 2 | Język programowania | Java |
| 3 |  OpenJDK Runtime Environment | build 14.0.1+7  |
| 4 | javac | javac 14.0.1 |
| 5 | GUI | Swing |
| 6 | IDE | IntelliJ IDEA 2020.1.1 (Community Edition) |
| 7 | Documentation | JavaDoc |    

Program realizuje znaną wszystkim i bardzo popularną grę typu Snake. W naszej realizacji gry tytułowy wąż, co oczywiste, zwiększa swój rozmiar po zjedzeniu jabłka oraz dodano w miarę zdobywania punktów przez gracza zwiększanie prędkości poruszania się obiektu. Kolizja o własne ciało oraz o ściany działa prawidłowo.    
Gra jest napisana wielowątkowo, można wyróżnić wątki dla :
- Jabłka (AppleThread)
- Sprawdzania kolizji (ColisionCheckThread)
- Odświeżania planszy (RepaintThread)
- Węża (SnakeThread)
# Więcej o grze

[Wikipedia](https://pl.wikipedia.org/wiki/W%C4%85%C5%BC_(gra_komputerowa)) 


