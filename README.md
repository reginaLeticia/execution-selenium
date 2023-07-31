# execution-selenium

Para executar os testes de alguns dos projetos você deve:
- Entrar no projeto e seguir seu passo a passo para rodar a aplicação
- Substituir o executável do chromedriver para uma versão compatível com o seu google Chrome
- Na raíz do diretório executar o comando `mvn test`

Para encontrar uma versão do chromedriver compatível com seu Google Chrome é necessário: 
- Acessar o Google Chrome
- Clicar em três pontinhos > Ajuda > Sobre o google chrome para verificar a versão do seu google chrome
- No site https://chromedriver.chromium.org/downloads baixar a versão executável compatível com a sua versão do google chrome
- Navegar no projeto em `src/main/resources` e substituir o chromedriver pela versão que você acabou de instalar

(A versão do chromedriver presente nos projetos é compatível com a versão `Versão 114.0.5735.198 (Versão oficial) 64 bits` do Google Chrome)
