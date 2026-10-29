import {Image} from "@/resources/image/image.resource";

class ImageService {
    baseUrl: string = "http://localhost:8080/api/images";

    async buscar() : Promise<Image[]> {
        const response = await fetch(this.baseUrl);
        return await response.json();
    }
}

export const useImageService = () => {
    return new ImageService();
};