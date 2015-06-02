# NBA_Finals_2015_Live
Java desktop application specified for text and image live of 2015 NBA finals.
数据层并没有向UI展示层提供数据读取接口，而是采用了单例模式、观察者模式相结合的方式刷新后台数据并及时通知UI并动态展示，因而提高了效率并减少了代码量
