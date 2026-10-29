interface ImageCardProps {
    nome?: string;
    tamanho?: string;
    dataUpload?: string;
    src?: string;
}


export const ImageCard: React.FC<ImageCardProps> = ({ nome, tamanho, dataUpload, src }: ImageCardProps) => {
  return (
    <div className="card relative bg-white rounded-md shadow-md transition-transform
    easy-in duration-300 transform hover:shadow-lg hover:-translate-y-2">
      <img src={src} className="w-full h-56 object-cover rounded-t-md" alt="" />
      <div className="card-body p-4">
        <h5 className="text-lg font-semibold">{nome}</h5>
        <p className="text-gray-600">{tamanho}</p>
        <p className="text-gray-600">{dataUpload}</p>
      </div>
    </div>

  );
}
