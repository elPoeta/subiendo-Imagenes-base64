let archivo;
let imagen = {};
const URL_IMAGENES = 'api/imagenServer';
const selectFile = document.querySelector('#selectFile');
const btnTraerImg = document.querySelector('#btn-traer');

selectFile.addEventListener('change', (e)=>{
  let files = e.target.files;

  if (files[0] && files[0].type.match(/^image\//)) {
      
      let fileReader= new FileReader();
            
      fileReader.readAsDataURL(files[0]);
        
      archivo = files[0];
      
      fileReader.addEventListener("load", function(e) {
        archivo.resultado = e.target.result;
        document.querySelector('#thumb-template').innerHTML='';
       
        let img = document.createElement('img');
        img.setAttribute('id', 'img');
        img.src = archivo.resultado;
        img.style.height='150px';
        document.querySelector('#thumb-template').appendChild(img);
       
        let btnSubir = document.createElement('button');
        btnSubir.setAttribute('id', 'btn-subir');
        btnSubir.innerText='Subir Imagen';
        document.querySelector('#thumb-template').appendChild(btnSubir);
       
        document.querySelector('#base64').innerHTML = archivo.resultado;
        let template = 
        `<p>Nombre: ${archivo.name}</p>
        <p>Size: ${archivo.size} bytes</p>
        <p>Tipo: ${archivo.type}</p>`;
        document.querySelector('#info').innerHTML = template;
       
        btnSubir.addEventListener('click', ()=>{

          imagen.nombre = archivo.name;
          imagen.fileSize = archivo.size;
          imagen.fileType = archivo.type;
          imagen.imgBase64 = archivo.resultado;
          Http.post(URL_IMAGENES, imagen)
              .then(data => {
                  console.log(data);
          })
            .catch (error => {
                console.log(error);
          });
        
        });
        
      }); 
 
    }else{
      let template ='';
      if(files[0]){
        template =`<p><strong>El archivo seleccionado no es una imagen, es de tipo: ${files[0].type}</strong></p>`;  
      }
        
      document.querySelector('#thumb-template').innerHTML = template;
      document.querySelector('#base64').innerHTML = '';
      document.querySelector('#info').innerHTML = '';
    }
    
});

btnTraerImg.addEventListener('click', ()=>{
    Http.get(URL_IMAGENES)
    .then(data => {
      mostrarTodas(data);
      })
        .catch(error =>{
            console.log(error);
      });
});

function mostrarTodas(imagenes){
  let template =
  `${imagenes.map(imagen =>
   `<img src="${imagen.imgBase64}" height="350">` 
  ).join('')}`;
  document.querySelector('#template-img').innerHTML=template;
}

