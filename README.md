# Estoque de Medicamentos

Medicinestock é um aplicativo Android desenvolvido com **Jetpack Compose** para gerenciar o estoque de medicamentos. O app permite o registro, visualização e controle de medicamentos, com funcionalidades como adição, remoção e atualização de quantidade dos produtos.

## Funcionalidades

- **Cadastro de medicamentos**: Adicione medicamentos ao estoque com informações como nome e quantidade.
- **Controle de estoque**: Visualize o estoque atual de medicamentos e verifique as quantidades disponíveis.
- **Armazenamento local com Room**: Todos os dados são armazenados localmente no dispositivo utilizando o banco de dados **Room**.

## Tecnologias Utilizadas

- **Android** com **Jetpack Compose**
- **Room** para gerenciamento de banco de dados local
- **Kotlin** como linguagem principal
- **MVVM** (Model-View-ViewModel) para organização do código

---

## 💾 Banco de Dados - Room

Utilizei o Room para persistência local. Ele é composto por três partes principais:

- **Entidade (`Medicine.kt`)**: representa a tabela no banco.
- **DAO (`MedicineDao.kt`)**: define as operações como inserir, atualizar, deletar e listar medicamentos.
- **Banco (`MedicineDatabase.kt`)**: configura o Room e fornece acesso ao DAO.

---

## 🔄 Repository

A camada de **repositório** faz a ponte entre o DAO e as ViewModels. Assim, a lógica de acesso aos dados fica separada da interface e da lógica de apresentação.

---

## 📱 Interface do Usuário (Jetpack Compose)

A UI é toda feita com **Jetpack Compose**. O projeto está dividido por telas e componentes:

- **screens/**: contém composables para cada tela (ex: lista de medicamentos).
- **components/**: botões, caixas de texto e outros elementos reutilizáveis.

Cada tela observa um ViewModel, que fornece os dados e lida com ações do usuário.

---

## 🔧 ViewModel

As ViewModels são responsáveis por:

- Armazenar e expor o estado da UI (com `State` ou `StateFlow`)
- Chamar métodos do repositório para obter ou modificar dados
- Gerenciar eventos da interface de forma desacoplada

---

### Requisitos

- Android Studio (última versão)
- SDK Android 30+

