interface TemplateProps {
    children: React.ReactNode;
    loading?: boolean;
}

export const Template: React.FC<TemplateProps> = ({ children, loading = false }: TemplateProps) => {
    return (
        <>

            <Header />
                <div className = {`container mx-auto mt-8 px-4 ${loading ? "animate-pulse" : ""}`}>
                    <RenderIf condition={loading}>
                        <Loading />
                    </RenderIf>
                    {children}
                </div>

            <Footer />

        </>
    )
}

interface RenderIfProps {
    condition?: boolean;
    children: React.ReactNode;
}

export const RenderIf: React.FC<RenderIfProps> = ({ condition = true, children }: RenderIfProps) => {
    if (condition) {
        return children;
    }
    return false;
}

const Loading: React.FC = () => {
    return (
        <div className = "text-center">
            <div role="status"
                 className = "inline-block h-8 w-8 animate-spin rounded-full border-4 border-gray-300 border-t-red-600">
                <span className = "sr-only">Carregando...</span>
            </div>
        </div>
    )
}

const Header: React.FC = () => {
    return (
        <header className = "bg-red-600 text-white py-3">
          <div className = "container mx-auto items-center justify-between px-4">
            <h1 className = "text-3xl font-bold">Imagem Peças</h1>
          </div>
        </header>
    )
}

const Footer: React.FC = () => {
    return (
        <footer className = "bg-red-600 text-white py-3">
          <div className = "container mx-auto text-center">
            <p>&copy; {new Date().getFullYear()} Imagem Peças. Todos os direitos reservados.</p>
            <p>Desenvolvido por Eduardo Mosti</p>
          </div>
        </footer>
    )
}