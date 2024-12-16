
const SearchQuery = ({ setSearchQuery }) => {

    const handleSearch = (e) => {

        setSearchQuery(e.target.value.toLowerCase());
    }

    return (
        <div className={'bg-white rounded-xl mt-5 flex flex-col justify-center'}>
            <div className={'text-3xl'}>Search Bar:</div>
            <div className={'text-2xl mt-5'}>Search based on Color, Year and Make!</div>
            <input className={'h-[50px] border-2 border-black text-xl'} type="text" onChange={handleSearch}/>
        </div>
    )
}

export default SearchQuery;