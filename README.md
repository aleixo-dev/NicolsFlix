
<p align=center>
<h2 align="center">NicolsFlix</h2>
<img width=250 src="https://i.imgur.com/JNCNIYZ.png"></img>
</p>

<p align=center>
<img width=200 align=center src="https://i.imgur.com/Mb8Y6uD.png"></img>
<img width=200 align=center src="https://i.imgur.com/h8gxVpy.png"></img>
<img width=200 align=center src="https://i.imgur.com/Y2fctor.png"></img>
<img width=200 align=center src="https://i.imgur.com/BFqBUI8.png"></img>
</p>

### 🚀 Projeto 
<p>📝 NicolsFlix, aplicativo que exibe filmes populares,recomendados, tendências, filmes similares ao qual você selecionou, onde é possível salvar o filme escolhido. Ao salvar o filme ele irá direto a sua lista de filmes. Api usada no projeto para exibição dos filmes foi a [TheMovieDb](https://www.themoviedb.org/)

### 🔧 Arquitetura
- > MVVM  Model - View - ViewModel
	- Arquitetura recomendada pelo Google para construção de aplicativos

### 🔗 Recursos usados
- **Retrofit2**
> Retrofit,  Um cliente HTTP de tipo seguro para Android e Java.
- **Picasso**
> Picasso, Uma poderosa biblioteca de download e cache de imagens para Android.
- **Gson**
> Gson, é uma biblioteca Java que pode ser usada para converter objetos Java em sua representação JSON.
- **ViewModel**
> ViewModel, Os componentes de arquitetura fornecem a classe auxiliar ViewModel para controlador de UI, que é responsável por preparar os dados para a UI.
- **LiveData**
> LiveData, é uma classe armazenadora de dados observável. Diferente de um observável comum, o LiveData conta com reconhecimento de ciclo de vida, ou seja, ele respeita o ciclo de vida de outros componentes do app.
- **Room**
> Room, fornece uma longa camada de abstração sobre o SQLite para permitir o acesso fluente ao banco de dados enquanto aproveita todo o poder do SQLite.
- **Koin** 
> Koin, Uma estrutura de injeção de dependência pragmática e leve para desenvolvedores Kotlin.
- **SafeArgs**
> SafeArgs, permite que você anexe dados a uma operação de navegação, definindo argumentos para um destino, ou seja, destino de perfil de usuário pode receber um argumento de ID de usuário para determinar qual exibir.

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
