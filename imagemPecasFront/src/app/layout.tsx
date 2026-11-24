import type { Metadata } from "next";
import { Roboto } from "next/font/google";
import "./globals.css";

const roboto = Roboto({subsets: ["latin"], weight: "400"});

export const metadata: Metadata = {
  title: "Imagem Peças",
  description: "Galeria online de imagens de peças industriais",
};

export default function RootLayout({ children }: LayoutProps<"/">) {
  return (
    <html
      lang="pt-BR"
      className={`${roboto.className} h-full antialiased`}
    >
      <body className="min-h-full flex flex-col">{children}</body>
    </html>
  );
}