interface ImageCardProps {
    nome?: string;
    extension?: string;
    tamanho?: number;
    dataUpload?: string;
    src?: string;
}


export const ImageCard: React.FC<ImageCardProps> = ({ nome, extension, tamanho = 0, dataUpload, src }: ImageCardProps) => {

  function download() {
    window.open(src, '_blank');
  }

  return (
    <div className="card relative bg-white rounded-md shadow-md transition-transform
    easy-in duration-300 transform hover:shadow-lg hover:-translate-y-2">
      <img src={src} className="w-full h-56 object-cover rounded-t-md" alt="" onClick={download} />
      <div className="card-body p-4">
        <h5 className="text-lg font-semibold">{nome}</h5>
        <p className="text-gray-600">{extension}</p>
        <p className="text-gray-600">{formatBytes(tamanho)}</p>
        <p className="text-gray-600">{dataUpload}</p>
      </div>
    </div>

  );
}

function formatBytes(bytes: number, decimals: number = 2) {
  if (!+bytes) return '0 Bytes'

  const k = 1024
  const dm = decimals < 0 ? 0 : decimals
  const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB']

  const i = Math.floor(Math.log(bytes) / Math.log(k))

  return `${parseFloat((bytes / Math.pow(k, i)).toFixed(dm))} ${sizes[i]}`
}