import Image from "next/image";
import { PrimeiroComponente, ArrowFunction } from "../components/PrimeiroComponente";

export default function Home() {
  return (
    <>
      <PrimeiroComponente 
      mensagem="Passando a mensagem como Parâmetro" 
      mensagemBotao="Passando a mensagem do botão como Parâmetro" />

      <PrimeiroComponente 
      mensagem="mais um componente criado" 
      mensagemBotao="Passando a mensagem do segundo componente agora" />
      <ArrowFunction />
    </>
  );
}
