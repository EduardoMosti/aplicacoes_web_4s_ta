'use client'

import { Template, ImageCard } from '@/components'
import { Image } from '@/resources/image/image.resource'
import { useImageService } from '@/resources/image/image.service'
import { useState } from 'react'

export default function GaleriaPage() {

  const useService = useImageService();
  const [images, setImages] = useState<Image[]>([]);
  const [query, setQuery] = useState<string>('');
  const [extension, setExtension] = useState<string>('');

  async function searchImages() {
    const result = await useService.buscar(query, extension);
    setImages(result);
    console.log(query);
  }

  function renderImageCard(image: Image) {
    return (
      <ImageCard key={image.url}
                 nome={image.name}
                 tamanho={image.size}
                 dataUpload={image.uploadDate}
                 src={image.url} />
    )
  }

  function renderImageCards() {
    return images.map(renderImageCard);
  }

  return (
    <Template>
      <section className="flex flex-col items-center justify-center my-3">
        <div className="flex space-x-4">
          <input type="text"
                 className="border px-5 py-2 rounded-lg text-gray-900"
                 onChange={event => setQuery(event.target.value)} />
          <select className="border px-4 py-2 rounded-lg text-gray-900"
                  onChange={event => setExtension(event.target.value)}>
            <option value="">All Formats</option>
            <option value="PNG">PNG</option>
            <option value="JPG">JPG</option>
            <option value="GIF">GIF</option>
          </select>
          <button className="bg-red-500 text-white px-4 py-2 rounded-lg" onClick={searchImages}>
            Search
          </button>
          <button className="bg-yellow-500 text-white px-4 py-2 rounded-lg">
            Add New
          </button>
        </div>
      </section>
      <section className="grid grid-cols-5 gap-8">
        {renderImageCards()}
      </section>
    </Template>
  );
}