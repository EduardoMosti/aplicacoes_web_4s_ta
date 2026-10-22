'use client';

interface PrimeiroComponenteProps {
    mensagem?: string;
    mensagemBotao?: string;
}
export const PrimeiroComponente: React.FC<PrimeiroComponenteProps> = 
(props: PrimeiroComponenteProps) => {

    function handleClick() {
        console.log(props.mensagemBotao)
        }

    return (
        <div>
            <p>{props.mensagem}</p>

            <button onClick={handleClick}>Clique aqui</button>
        </div>
  );
}

export const ArrowFunction = () => {
    return (
        <h2>ArrowFunction</h2>
    )
}