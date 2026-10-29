'use client'

import {Template, ImageCard} from '@/components'
import { useState} from 'react'

export default function GaleriaPage() {

  const image1 = 'https://t.ctcdn.com.br/UUMms_Aze7aK9scWHcsNBgRb4PE=/0x87:2560x1529/640x360/smart/i331446.jpeg'
  const image2 = 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWKaF6a7dkCwRIKgqkq6HzRx___vN8nkr8iIlJwUywrQ&s=10'

  const nome1 = 'Placa Mãe'
  const nome2 = 'Processador'

  const [codigoImage, setCodigoImage] = useState<number>(1);
  const [urlImage, setUrlImage] = useState<string>(image1);

  const [nomeImage, setNomeImage] = useState<string>(nome1);

  function mudarImagem() {
    if (codigoImage == 1) {
      setCodigoImage(2);
      setUrlImage(image2);
      setNomeImage(nome2);
    } else {
      setCodigoImage(1);
      setUrlImage(image1);
      setNomeImage(nome1);
    }
  }
  return (
    <Template>
      <button className="bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded" onClick={mudarImagem}>
        Clique para Mudar
      </button>
      <section className="grid grid-cols-5 gap-8">
        <ImageCard nome={nomeImage} tamanho="10MB" dataUpload="28/10/2026" src={urlImage} />
        <ImageCard nome={nomeImage} tamanho="10MB" dataUpload="28/10/2026" src={urlImage} />
        <ImageCard nome={nomeImage} tamanho="10MB" dataUpload="28/10/2026" src={urlImage} />
      </section>
    </Template>
  );
}
