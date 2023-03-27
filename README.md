
<br></br>
<p align=center>
<img width=200 src="https://user-images.githubusercontent.com/75820713/181814005-b512fc81-73a0-4547-ac47-63411dc73344.png"></img>
</p>


### 🚀 Project 
NicolasFlix, an app to display top current and similar movies you have selected, you can also search and save. 
#####  Api used in the protect:  [The Movie DB](https://www.themoviedb.org/)



<p align=center>
<img width=150 align=center src="https://user-images.githubusercontent.com/75820713/181812847-d78de437-ed73-4fcf-bafe-3a676daf1874.png"></img>
<img width=150 align=center src="https://user-images.githubusercontent.com/75820713/181812913-c440300a-c926-4f35-99aa-2a4757ef5994.png"></img>
<img width=150 align=center src="https://user-images.githubusercontent.com/75820713/181812946-e372ff8c-8c81-42ca-8631-d44d81936326.png"></img>
<img width=150 align=center src="https://user-images.githubusercontent.com/75820713/181812990-64faad69-87ba-4dd9-9673-dca736df7834.png"></img>
<img width=150 align=center src="https://user-images.githubusercontent.com/75820713/181812998-c8e0f59b-09b9-47ad-bceb-8abc4d1a0c7a.png"></img>
</p>


### 🔧 Arquitetura
- > MVVM  Model - View - ViewModel
	- Arquitetura recomendada pelo Google para construção de aplicativos
	- <p align=center> <img width=400 align=center src="https://i.imgur.com/W8iEzjo.png"></img> </p>

### 🔗 Recursos usados
- **Shimmer**
> Biblioteca que fornece uma maneira fácil de adicionar um efeito de brilho a qualquer exibição em seu aplicativo Android
- **Retrofit2**
> Um cliente HTTP de tipo seguro para Android e Java.
- **Picasso**
> Uma poderosa biblioteca de download e cache de imagens para Android.
- **Gson**
> É uma biblioteca Java que pode ser usada para converter objetos Java em sua representação JSON.
- **ViewModel**
> Os componentes de arquitetura fornecem a classe auxiliar ViewModel para controlador de UI, que é responsável por preparar os dados para a UI.
- **Room**
> Fornece uma longa camada de abstração sobre o SQLite para permitir o acesso fluente ao banco de dados enquanto aproveita todo o poder do SQLite.
- **Koin** 
> Uma estrutura de injeção de dependência pragmática e leve para desenvolvedores Kotlin.
- **SafeArgs**
> Permite que você anexe dados a uma operação de navegação, definindo argumentos para um destino, ou seja, destino de perfil de usuário pode receber um argumento de ID de usuário para determinar qual exibir.

### 🧰 Dependências utilizadas 
```
* SafeArgs
apply plugin: "androidx.navigation.safeargs"

def room_version = "2.2.6"

* Material.
implementation 'com.google.android.material:material:1.2.1'

* ViewModel.
implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.2.0'
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0'

* Fragment-KTX.
implementation "androidx.fragment:fragment-ktx:1.2.5"

* Room  
implementation "androidx.room:room-runtime:$room_version"  
kapt "androidx.room:room-compiler:$room_version"  
implementation "androidx.room:room-ktx:$room_version"

* Picasso  
implementation 'com.squareup.picasso:picasso:2.71828'

* Retrofit & Gson  
implementation 'com.squareup.retrofit2:retrofit:2.9.0'  
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

```

## 👦 Autor
> Nicolas Aleixo
> [Gmail](nicolasaleixo2020@gmail.com)
> [Instagram](https://www.instagram.com/nicolas09aa/)
> [Linkedin](https://www.linkedin.com/in/nicolas-aleixo/)
